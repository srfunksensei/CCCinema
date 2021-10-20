package com.mb.service.impl;

import com.mb.dto.AvailableSeatsForScreening;
import com.mb.dto.SeatDto;
import com.mb.models.Reservation;
import com.mb.models.Screening;
import com.mb.models.Seat;
import com.mb.models.SeatReserved;
import com.mb.repository.ReservationRepository;
import com.mb.repository.ScreeningRepository;
import com.mb.repository.SeatReservedRepository;
import com.mb.service.SeatService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SeatServiceImpl implements SeatService {

	private final ScreeningRepository screeningRepo;
	private final ReservationRepository reservationRepo;
	private final SeatReservedRepository seatReservedRepo;

	@Override
	public AvailableSeatsForScreening getSeats(long screeningId) {
		final Screening screening = screeningRepo.findById(screeningId)
				.orElseThrow(NotFoundException::new);

		ModelMapper mapper = new ModelMapper();
		Type listType = new TypeToken<List<SeatDto>>() {}.getType();
		List<SeatDto> seats = mapper.map(new ArrayList<>(getAvailableSeats(screening)), listType);

		return new AvailableSeatsForScreening(screeningId, seats);
	}

	@Override
	@Transactional
	public String bookSeat(long screeningId, String seat, String username) {
		final Screening screening = screeningRepo.findById(screeningId)
				.orElseThrow(NotFoundException::new);

		Set<Seat> availableSeats = getAvailableSeats(screening);

		if(isSeatAvailable(availableSeats, seat)){
			Reservation res = new Reservation();
			res.setReserved(true);
			res.setUsername(username);
			res.setScreening(screening);

			reservationRepo.save(res);

			SeatReserved sres = new SeatReserved();
			sres.setReservation(res);
			sres.setScreening(screening);
			sres.setSeat(getSeat(availableSeats, seat).get());

			seatReservedRepo.save(sres);

			return "Seat reserved successfully";
		}

		return "Seat already reserved";
	}

	private boolean isSeatAvailable(Set<Seat> availableSeats, String seat){
		return getSeat(availableSeats, seat).isPresent();
	}
	
	private Optional<Seat> getSeat(Set<Seat> availableSeats, String seat) {
		String row = seat.substring(0, 1);
		int num = Integer.parseInt(seat.substring(1));
		
		return availableSeats.stream().filter(p -> p.getNum() == num && p.getRow().equalsIgnoreCase(row)).findAny();
	}

	private Set<Seat> getAvailableSeats(Screening screening) {
		Set<Seat> reserved = screening.getSeatsReserved().stream().map(SeatReserved::getSeat)
				.collect(Collectors.toSet());

		Set<Seat> availableSeats = screening.getAuditorium().getSeats();
		availableSeats.removeAll(reserved);
		
		return availableSeats;
	}
}

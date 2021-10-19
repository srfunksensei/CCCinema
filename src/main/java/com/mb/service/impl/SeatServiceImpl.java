package com.mb.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private ScreeningRepository screeningRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private SeatReservedRepository seatReservedRepo;

	@Override
	public AvailableSeatsForScreening getSeats(long screeningId) {
		Screening screening = screeningRepo.findOne(screeningId);

		List<SeatDto> seats = new ArrayList<>();
		if (screening != null) {
			ModelMapper mapper = new ModelMapper();
			Type listType = new TypeToken<List<SeatDto>>() {}.getType();
			seats = mapper.map(new ArrayList<>(getAvailableSeats(screening)), listType);
		}

		return new AvailableSeatsForScreening(screeningId, seats);
	}

	@Override
	@Transactional
	public String bookSeat(long screeningId, String seat, String username) {
		Screening screening = screeningRepo.findOne(screeningId);
		
		if (screening != null) {
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
		
		return "No such screening available";
	}

	private boolean isSeatAvailable(Set<Seat> availableSeats, String seat){
		return getSeat(availableSeats, seat).isPresent();
	}
	
	private Optional<Seat> getSeat(Set<Seat> availableSeats, String seat) {
		String row = seat.substring(0, 1);
		int num = Integer.valueOf(seat.substring(1, seat.length()));
		
		return availableSeats.stream().filter(p -> p.getNum() == num && p.getRow().equalsIgnoreCase(row)).findAny();
	}

	private Set<Seat> getAvailableSeats(Screening screening) {
		Set<Seat> reserved = screening.getSeatsReserved().stream().map(SeatReserved::getSeat)
				.collect(Collectors.toSet());

		Set<Seat> availableSeats = screening.getAuditorium().getSeats();
		availableSeats.removeAll(reserved);
		
		return availableSeats;
	}
	
	public ScreeningRepository getScreeningRepo() {
		return screeningRepo;
	}

	public void setScreeningRepo(ScreeningRepository screeningRepo) {
		this.screeningRepo = screeningRepo;
	}
}

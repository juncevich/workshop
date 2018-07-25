package com.rfb.bootstrap;

import com.rfb.domain.RfbEvent;
import com.rfb.domain.RfbEventAttendance;
import com.rfb.domain.RfbLocation;
import com.rfb.domain.User;
import com.rfb.repository.RfbEventAttendanceRepository;
import com.rfb.repository.RfbEventRepository;
import com.rfb.repository.RfbLocationRepository;
import com.rfb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class RfbBootstrap implements CommandLineRunner {
    private final RfbLocationRepository rfbLocationRepository;
    private final RfbEventRepository rfbEventRepository;
    private final RfbEventAttendanceRepository rfbEventAttendanceRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RfbBootstrap(RfbLocationRepository rfbLocationRepository, RfbEventRepository rfbEventRepository, RfbEventAttendanceRepository rfbEventAttendanceRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.rfbLocationRepository = rfbLocationRepository;
        this.rfbEventRepository = rfbEventRepository;
        this.rfbEventAttendanceRepository = rfbEventAttendanceRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        if (rfbLocationRepository.count() == 0) {
            initData();
        }
    }

    private void initData() {
        User user = new User();
        user.setFirstName("John");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setLogin("john");
        user.setActivated(true);
        userRepository.save(user);

        RfbLocation aleAndWith = getRfbLocation("St Pete - Ale and the Witch", DayOfWeek.MONDAY.getValue());
        user.setHomeLocation(aleAndWith);
        userRepository.save(user);

        RfbEvent aleEvent = getRfbEvent(aleAndWith);
        getRfbEventAttendance(user, aleEvent);

        RfbLocation ratc = getRfbLocation("St Pete - Right Around The Corner", DayOfWeek.TUESDAY.getValue());
        RfbEvent ratcEvent = getRfbEvent(ratc);
        getRfbEventAttendance(user, ratcEvent);

        RfbLocation stPeteBrew = getRfbLocation("St Pete - St Pete Brewing", DayOfWeek.WEDNESDAY.getValue());
        RfbEvent stPeteBrewEvent = getRfbEvent(stPeteBrew);
        getRfbEventAttendance(user, stPeteBrewEvent);

        RfbLocation yardOfAle = getRfbLocation("St Pete - Yard of Ale", DayOfWeek.THURSDAY.getValue());
        RfbEvent yardOfAleEvent = getRfbEvent(yardOfAle);
        getRfbEventAttendance(user, yardOfAleEvent);

    }

    private void getRfbEventAttendance(User user, RfbEvent rfbEvent) {
        RfbEventAttendance rfbEventAttendance = new RfbEventAttendance();
        rfbEventAttendance.setRfbEvent(rfbEvent);
        rfbEventAttendance.setUser(user);
        rfbEventAttendance.setAttendanceDate(LocalDate.now());

        System.out.println(rfbEventAttendance.toString());

        rfbEventAttendanceRepository.save(rfbEventAttendance);
        rfbEventRepository.save(rfbEvent);

    }

    private RfbEvent getRfbEvent(RfbLocation rfbLocation) {
        RfbEvent rfbEvent = new RfbEvent();
        rfbEvent.setEventCode(UUID.randomUUID().toString());
        rfbEvent.setEventDate(LocalDate.now());

        rfbLocation.addRvbEvent(rfbEvent);
        rfbLocationRepository.save(rfbLocation);
        rfbEventRepository.save(rfbEvent);
        return rfbEvent;
    }

    private RfbLocation getRfbLocation(String locationName, int value) {
    RfbLocation rfbLocation = new RfbLocation();
    rfbLocation.setLocationName(locationName);
    rfbLocation.setRunDayOfTheWeek(value);
    rfbLocationRepository.save(rfbLocation);
    return rfbLocation;
    }
}

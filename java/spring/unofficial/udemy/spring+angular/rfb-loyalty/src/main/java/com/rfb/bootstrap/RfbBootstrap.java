package com.rfb.bootstrap;

import com.rfb.domain.RfbEvent;
import com.rfb.domain.RfbEventAttendance;
import com.rfb.domain.RfbLocation;
import com.rfb.domain.RfbUser;
import com.rfb.repository.RfbEventAttendanceRepository;
import com.rfb.repository.RfbEventRepository;
import com.rfb.repository.RfbLocationRepository;
import com.rfb.repository.RfbUserRepository;
import org.springframework.boot.CommandLineRunner;
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
    private final RfbUserRepository rfbUserRepository;

    public RfbBootstrap(RfbLocationRepository rfbLocationRepository, RfbEventRepository rfbEventRepository, RfbEventAttendanceRepository rfbEventAttendanceRepository, RfbUserRepository rfbUserRepository) {
        this.rfbLocationRepository = rfbLocationRepository;
        this.rfbEventRepository = rfbEventRepository;
        this.rfbEventAttendanceRepository = rfbEventAttendanceRepository;
        this.rfbUserRepository = rfbUserRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        if (rfbLocationRepository.count() == 0) {
            initData();
        }
    }

    private void initData() {
        RfbUser rfbUser = new RfbUser();
        rfbUser.setUserName("John");
        rfbUserRepository.save(rfbUser);

        RfbLocation aleAndWith = getRfbLocation("St Pete - Ale and the Witch", DayOfWeek.MONDAY.getValue());
        rfbUser.setHomeLocation(aleAndWith);
        rfbUserRepository.save(rfbUser);

        RfbEvent aleEvent = getRfbEvent(aleAndWith);
        getRfbEventAttendance(rfbUser, aleEvent);

        RfbLocation ratc = getRfbLocation("St Pete - Right Around The Corner", DayOfWeek.TUESDAY.getValue());
        RfbEvent ratcEvent = getRfbEvent(ratc);
        getRfbEventAttendance(rfbUser, ratcEvent);

        RfbLocation stPeteBrew = getRfbLocation("St Pete - St Pete Brewing", DayOfWeek.WEDNESDAY.getValue());
        RfbEvent stPeteBrewEvent = getRfbEvent(stPeteBrew);
        getRfbEventAttendance(rfbUser, stPeteBrewEvent);

        RfbLocation yardOfAle = getRfbLocation("St Pete - Yard of Ale", DayOfWeek.THURSDAY.getValue());
        RfbEvent yardOfAleEvent = getRfbEvent(yardOfAle);
        getRfbEventAttendance(rfbUser, yardOfAleEvent);

    }

    private void getRfbEventAttendance(RfbUser rfbUser, RfbEvent rfbEvent) {
        RfbEventAttendance rfbEventAttendance = new RfbEventAttendance();
        rfbEventAttendance.setRfbEvent(rfbEvent);
        rfbEventAttendance.setRfbUser(rfbUser);
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

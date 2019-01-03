import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { LoginModalService, Principal, Account, User, AccountService } from 'app/core';
import { IRfbLocation, RfbLocation } from 'app/shared/model/rfb-location.model';
import { RfbEvent } from 'app/shared/model/rfb-event.model';
import { RfbEventAttendance } from 'app/shared/model/rfb-event-attendance.model';
import { EventManager } from '@angular/platform-browser';
import { RfbLocationService } from 'app/entities/rfb-location';
import { RfbEventService } from 'app/entities/rfb-event';
import { HttpResponse } from '@angular/common/http';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    isSaving: boolean;
    locations: RfbLocation[];
    todaysEvent: RfbEvent;
    currentUser: User;
    model: any;
    rfbEventAttendance: RfbEventAttendance;
    errors: any = { invalidEventCode: '' };
    checkedIn = false;

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: EventManager,
        private locationService: RfbLocationService,
        private eventService: RfbEventService,
        private accountService: AccountService
    ) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();
        this.loadLocations();
        this.model = { location: 0, eventCode: '' };
        this.rfbEventAttendance = new RfbEventAttendance(null, new Date(), new RfbEvent(), new User());

        this.accountService.get().subscribe((user: User) => {
            this.currentUser = user;
            this.rfbEventAttendance.rfbUserDto = user;

            if (this.currentUser.authorities.indexOf('ROLE_ORGANIZER') !== -1) {
                this.setTodaysEvent(this.currentUser.homeLocation);
            }

            if (this.currentUser.authorities.indexOf('ROLE_RUNNER') !== -1) {
                this.model.location = this.currentUser.homeLocation;
            }
        });
    }

    setTodaysEvent(locationID: number) {
        this.eventService.findByLocation(locationID).subscribe((rfbEvent: RfbEvent) => {
            this.todaysEvent = rfbEvent;
        });
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', message => {
            this.principal.identity().then(account => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    loadLocations() {
        this.locationService
            .query({
                page: 0,
                size: 100,
                sort: ['locationName', 'ASC']
            })
            .subscribe(
                (res: HttpResponse<IRfbLocation[]>) => {
                    this.locations = res.body;
                },
                (res: Response) => {
                    console.log(res);
                }
            );
    }
}

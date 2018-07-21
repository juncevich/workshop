import { Moment } from 'moment';
import { IRfbEventAttendance } from 'app/shared/model//rfb-event-attendance.model';
import { IRfbLocation } from 'app/shared/model/rfb-location.model';

export interface IRfbEvent {
    id?: number;
    eventDate?: Moment;
    eventCode?: string;
    rfbLocation?: IRfbLocation;
    rfbEventAttendances?: IRfbEventAttendance[];
}

export class RfbEvent implements IRfbEvent {
    constructor(
        public id?: number,
        public eventDate?: Moment,
        public eventCode?: string,
        public rfbLocationId?: number,
        public rfbEventAttendances?: IRfbEventAttendance[]
    ) {}
}

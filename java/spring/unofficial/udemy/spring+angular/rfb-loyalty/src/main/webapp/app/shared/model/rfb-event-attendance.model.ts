import { Moment } from 'moment';
import { IRfbEvent, RfbEvent } from 'app/shared/model/rfb-event.model';
import { IRfbUser, RfbUser } from 'app/shared/model/rfb-user.model';

export interface IRfbEventAttendance {
    id?: number;
    attendanceDate?: Moment;
    rfbEventDTO?: IRfbEvent;
    rfbUserDTO?: IRfbUser;
}

export class RfbEventAttendance implements IRfbEventAttendance {
    constructor(public id?: number, public attendanceDate?: Moment, public rfbEventId?: number, public rfbUserId?: number) {}
}

import { IRfbEvent } from 'app/shared/model//rfb-event.model';

export interface IRfbLocation {
    id?: number;
    locationName?: string;
    runDayOfTheWeek?: string;
    rvbEvents?: IRfbEvent[];
}

export class RfbLocation implements IRfbLocation {
    constructor(public id?: number, public locationName?: string, public runDayOfTheWeek?: string, public rvbEvents?: IRfbEvent[]) {}
}

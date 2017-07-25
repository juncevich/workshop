export class CounterService {
  toActiveOperationCount = 0;
  toInActiveOperationCount = 0;

  toActive() {
    this.toActiveOperationCount++;
    console.log('inactive->active ' + this.toActiveOperationCount);
  }

  toInactive() {
    this.toInActiveOperationCount++;
    console.log('active->inactive ' +
      this.toInActiveOperationCount
    )
    ;
  }

}

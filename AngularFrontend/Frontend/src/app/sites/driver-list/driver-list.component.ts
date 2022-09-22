import {Component, OnInit} from '@angular/core';
import {Driver} from '../../models/driver';
import {DriverService} from '../../services/driver-service';


@Component({
  selector: 'app-driver-list',
  templateUrl: './driver-list.component.html',
  styleUrls: ['./driver-list.component.css']
})
export class DriverListComponent implements OnInit {

  drivers: Driver[];
  selectedDriver: Driver;
  index: number;

  constructor(private driverService: DriverService) {
    this.index = 0;
  }

  ngOnInit() {
    this.driverService.getDrivers().subscribe(s => this.drivers = s);
  }

  onSelect(driver) {
    this.selectedDriver = driver;
    if (confirm('Czy na pewno chcesz usunąć kierowcę ' + this.selectedDriver.firstName + ' ' + this.selectedDriver.name)) {
      this.driverService.deleteDriver(driver).subscribe((data) => {
        alert('Poprawnie usunięto kierowcę ' + this.selectedDriver.firstName + ' ' + this.selectedDriver.name);
        this.ngOnInit();
      });
    }
  }

}

import {Component, OnInit} from '@angular/core';
import {Vehicle} from '../../models/vehicle';
import {VehicleServiceService} from '../../services/vehicle-service.service';
import {NgbDatepickerConfig, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {RentServiceService} from '../../services/rent-service.service';
import {Rent} from '../../models/rent';
import {DriverService} from '../../services/driver-service';
import {Driver} from '../../models/driver';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent implements OnInit {

  vehicles: Vehicle[];
  selectedVehicle: Vehicle;
  drivers: Driver[];
  index: number;
  inputBrand;
  inputModel;
  inputRegisterNo;
  myDate;
  inputCost;
  choosenDriver;
  maxDate = new Date();

  constructor(private vehicleService: VehicleServiceService, private modalService: NgbModal
    , private rentService: RentServiceService, private driverService: DriverService
    , private config: NgbDatepickerConfig) {
    this.index = 0;
    config.maxDate = {year: this.maxDate.getFullYear(), month: this.maxDate.getMonth(), day: this.maxDate.getDay()};
    config.outsideDays = 'hidden';
  }

  ngOnInit() {
    this.vehicleService.getVehicles().subscribe(s => this.vehicles = s);
    this.driverService.getDrivers().subscribe(s => this.drivers = s);
  }

  delete(vehicle) {
    if (this.isRented(vehicle) === 'NIE') {
      this.selectedVehicle = vehicle;
      if (confirm('Czy na pewno chcesz usunąć pojazd' + this.selectedVehicle.brand + ' ' + this.selectedVehicle.model
          + ' o numerze VIN: ' + this.selectedVehicle.vin)) {
        this.vehicleService.deleteVehicle(this.selectedVehicle).subscribe((data) => {
          alert('Poprawnie usunięto pojazd ' + this.selectedVehicle.brand + ' ' + this.selectedVehicle.model
            + ' o numerze VIN: ' + this.selectedVehicle.vin);
          this.ngOnInit();
        });
      }
    } else {
      alert('Nie można usunąć pojazdu ' + this.selectedVehicle.brand + ' ' + this.selectedVehicle.model
        + ' o numerze VIN: ' + this.selectedVehicle.vin + ', ponieważ jest aktualnie wynajmowany');
      this.ngOnInit();
    }
  }

  onRepair(vehicle) {
    if (vehicle.repair) {
      return 'TAK';
    } else {
      return 'NIE';
    }
  }

  setRepair(value, vehicle) {
    vehicle.repair = value;
    this.vehicleService.setRepair(vehicle).subscribe((data) => {
      alert('Poprawnie zmieniono status naprawy w aucie ' + this.selectedVehicle.brand + ' ' + this.selectedVehicle.model
        + ' o numerze VIN: ' + this.selectedVehicle.vin);
    });
  }

  editData(vehicle, content) {
    this.selectedVehicle = vehicle;
    this.inputBrand = this.selectedVehicle.brand;
    this.inputModel = this.selectedVehicle.model;
    this.inputRegisterNo = this.selectedVehicle.registerNo;
    this.modalService.open(content, {windowClass: 'dark-modal'});
  }

  addRent(vehicle, content) {
    this.selectedVehicle = vehicle;
    if (vehicle.repair) {
      alert('Nie można wynająć pojazdu ' + this.selectedVehicle.brand + ' ' + this.selectedVehicle.model + ', ponieważ jest w naprawie.');
    } else {
      this.modalService.open(content, {windowClass: 'dark-modal'});
      this.ngOnInit();
    }
  }

  createRent(modal) {
    const rent = new Rent();
    rent.vehicle = this.selectedVehicle;
    rent.driverPesel = this.choosenDriver;
    rent.cost = +this.inputCost;
    const dateStr = this.myDate.month + '-' + this.myDate.day + '-' + this.myDate.year;
    rent.dateFrom = new Date(dateStr);
    this.rentService.addRent(rent).subscribe((data) => {
      alert('Poprawnie dodano wynajem ' + this.selectedVehicle.brand + ' ' + this.selectedVehicle.model
        + ', do kierowcy.');
      this.ngOnInit();
    });
    modal.close();
  }

  editVehicle(modal) {
    this.selectedVehicle.brand = this.inputBrand;
    this.selectedVehicle.model = this.inputModel;
    this.selectedVehicle.registerNo = this.inputRegisterNo;
    this.selectedVehicle.repair = false;
    this.vehicleService.saveVehicle(this.selectedVehicle).subscribe((data) => {
      alert('Poprawnie zmieniono dane pojazdu ' + this.selectedVehicle.brand + ' ' + this.selectedVehicle.model);
    });
    modal.close();
  }

  isRented(vehicle) {
    if (vehicle.rentId == null) {
      return 'NIE';
    } else {
      return vehicle.driver.name + ' ' + vehicle.driver.firstName;
    }
  }

  endRent(vehicle) {
    this.selectedVehicle = vehicle;
    if (confirm('Czy na pewno chcesz zakończyć wynajmowanie pojazdu ' + vehicle.brand + ' ' + vehicle.model)) {
      this.rentService.endRent(vehicle.rentId).subscribe((data) => {
        alert('Poprawnie zakończono wynajem pojazdu ' + vehicle.brand + ' ' + vehicle.model);
        this.ngOnInit();
      });
    }
  }
}

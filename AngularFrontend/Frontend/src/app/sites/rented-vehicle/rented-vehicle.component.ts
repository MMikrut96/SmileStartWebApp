import {Component, OnInit} from '@angular/core';
import {Vehicle} from '../../models/vehicle';
import {RentServiceService} from '../../services/rent-service.service';
import {UserService} from '../../services/user.service';
import {Rent} from '../../models/rent';

@Component({
  selector: 'app-rented-vehicle',
  templateUrl: './rented-vehicle.component.html',
  styleUrls: ['./rented-vehicle.component.css']
})
export class RentedVehicleComponent implements OnInit {

  vehicles: Vehicle[];
  selectedVehicle: Vehicle;
  rents: Rent[];
  tmp = new Array();

  constructor(private rentService: RentServiceService, private userService: UserService) {
  }

  ngOnInit() {
    this.rentService.getDriverRent(this.userService.getUser()).subscribe(s => {
      this.rents = s;
      for (const rent of s) {
        this.tmp.push(rent.vehicle);
      }
    });
    this.vehicles = this.tmp;
  }

  endRent(vehicle) {
    this.selectedVehicle = vehicle;
    if (confirm('Czy na pewno chcesz zakończyć wynajmowanie pojazdu ' + vehicle.brand + ' ' + vehicle.model)) {
      this.rentService.endRent(vehicle.rentId).subscribe((data) => {
        alert('Poprawnie zakończono wynajem pojazdu ' + vehicle.brand + ' ' + vehicle.model);
        window.location.reload();
      });
    }
  }
}

import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {DriverService} from "../../services/driver-service";
import {Driver} from "../../models/driver";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-driver',
  templateUrl: './add-driver.component.html',
  styleUrls: ['./add-driver.component.css']
})
export class AddDriverComponent implements OnInit {

  inputFirstName;
  inputName;
  inputPesel;

  constructor(private driverService: DriverService, private router: Router) {
  }

  ngOnInit() {
  }

  clearForm() {
    this.inputFirstName = '';
    this.inputName = '';
    this.inputPesel = '';
  }

  addDriver() {
    const driver = new Driver();
    driver.firstName = this.inputFirstName;
    driver.name = this.inputName;
    driver.pesel = this.inputPesel;
    this.driverService.addDriver(driver).subscribe((data) => {
      alert('Poprawnie dodano kierowcę ' + driver.firstName + ' ' + driver.name);
    });
    this.driverService.addUser(driver.pesel).subscribe((data) => {
      alert('Poprawnie dodano konto dla ' + driver.firstName + ' ' + driver.name);
      this.router.navigateByUrl('driver-list');
    });
  }


}

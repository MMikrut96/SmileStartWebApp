import {Component, OnInit} from '@angular/core';
import {Profit} from '../../models/profit';
import {ProfitServiceService} from '../../services/profit-service.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {UserService} from '../../services/user.service';
import {RentServiceService} from "../../services/rent-service.service";
import {Rent} from "../../models/rent";
import {forEach} from "@angular/router/src/utils/collection";

@Component({
  selector: 'app-profit-list',
  templateUrl: './profit-list.component.html',
  styleUrls: ['./profit-list.component.css']
})
export class ProfitListComponent implements OnInit {

  profits: Profit[];
  selectedProfit: Profit;
  rents: Rent[];
  index: number;
  inputIncome;

  constructor(private profitService: ProfitServiceService, private rentService: RentServiceService, private modalService: NgbModal, private userService: UserService) {
    this.index = 0;
  }

  ngOnInit() {
    this.profitService.getMyProfits().subscribe(s => this.profits = s);
    this.rentService.getDriverRent(this.userService.getUser()).subscribe(s => this.rents = s);
  }

  editData(profit, content) {
    this.selectedProfit = profit;
    this.modalService.open(content, {windowClass: 'dark-modal'});
  }

  editProfit(modal) {
    this.selectedProfit.income = +this.inputIncome - this.countCosts();
    this.profitService.saveMyProfit(this.selectedProfit).subscribe((data) => {
      alert('Poprawnie zmieniono dane przychodu z ' + this.selectedProfit.date);
      this.ngOnInit();
    });
    modal.close();
  }

  addData(modal) {
    this.modalService.open(modal, {windowClass: 'dark-modal'});
  }

  addProfit(modal) {
    const profit = new Profit();
    profit.date = new Date();
    profit.driverPesel = this.userService.getUser();
    profit.income = +this.inputIncome - this.countCosts();
    this.profitService.addToMyProfits(profit).subscribe((data) => {
      alert('Poprawnie dodany przychód.');
      this.ngOnInit();
    });
    modal.close();
  }

  compareDate(date: Date) {
    const now = new Date();
    now.setHours(0, 0, 0, 0);
    if (new Date(date) >= now) {
      return true;
    } else {
      return false;
    }
  }

  countCosts() {
    if (this.profits[0] != null) {
      return this.profits[0].rentCost;
    } else {
      let count = 0;
      for (const r of this.rents) {
        count += r.cost;
      }
      return count;
    }
  }


}

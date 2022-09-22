import {Driver} from './driver';

export class Vehicle {
  brand: string;
  model: string;
  registerNo: string;
  vin: string;
  repair: boolean;
  rentId: number;
  driver: Driver;
}

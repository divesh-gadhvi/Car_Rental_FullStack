import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-book-car',
  templateUrl: './book-car.component.html',
  styleUrl: './book-car.component.scss'
})
export class BookCarComponent {

  carId: number = this.activatedRoute.snapshot.params["id"];
  car: any;
  processedImg: any;

  constructor(private customerService: CustomerService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.getCarById();
  }

  getCarById(){
    this.customerService.getCarByID(this.carId).subscribe((res) => {
      console.log(res);
      this.processedImg = 'data:image/jpeg;base64,' + res.returnedImage;
      this.car = res;
    })
  }
}

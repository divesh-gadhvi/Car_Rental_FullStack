import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrl: './update-car.component.scss',
})
export class UpdateCarComponent {
  isSpinning = false;
  carId: number = this.activateRoute.snapshot.params['id'];
  imgChanged: boolean = false;
  selectedFile: any;
  imagePreview!: string | ArrayBuffer | null;
  existingImage: string | null = null;
  updateForm!: FormGroup;
  listOfOptions: Array<{ label: string; value: string }> = [];
  listOfBrands = [
    'BMW',
    'AUDI',
    'FERRARI',
    'TESLA',
    'VOLVO',
    'TOYOTA',
    'HONDA',
    'FORD',
    'NISSAN',
    'HYUNDAI',
    'LEXUS',
    'KIA',
  ];
  listOfTypes = ['Petrol', 'Hybrid', 'Diesel', 'Electric', 'CNG'];
  listOfColor = ['Red', 'White', 'Blue', 'Black', 'Orange', 'Grey', 'Silver'];
  listOfTransmission = ['Manual', 'Automatic'];

  constructor(
    private adminService: AdminService,
    private activateRoute: ActivatedRoute,
    private fb: FormBuilder,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.updateForm = this.fb.group({
      name: [null, Validators.required],
      brand: [null, Validators.required],
      type: [null, Validators.required],
      color: [null, Validators.required],
      transmission: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
      year: [null, Validators.required],
    });
    this.getCarById();
  }

  getCarById() {
    this.isSpinning = true;
    this.adminService.getCarById(this.carId).subscribe((res) => {
      this.isSpinning = false;
      const carDTO = res;
      this.existingImage = 'data:image/jpeg;base64,' + res.returnedImage;
      this.updateForm.patchValue(carDTO);
    });
  }

  updateCar() {
    this.isSpinning = true;
    const payload = new FormData();
    if (this.imgChanged && this.selectedFile) {
      payload.append('image', this.selectedFile, this.selectedFile.name);
    }
    
    for (const key in this.updateForm.controls) {
      if (this.updateForm.controls.hasOwnProperty(key)) {
        payload.append(key, this.updateForm.controls[key].value);
      }
    }

    this.adminService.updateCar(this.carId, payload).subscribe(
      (res) => {
        this.isSpinning = false;
        this.message.success('Car updated successfully!', { nzDuration: 5000 });
        this.router.navigateByUrl('/admin/dashboard');
        console.log(res);
      },
      (error) => {
        console.error('Error: ', error);
        this.message.error('Error while updating car', { nzDuration: 5000 });
      }
    );
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    this.imgChanged = true;
    this.existingImage = null;
    this.previewImage();
  }

  previewImage() {
    if (this.selectedFile) {
      const reader = new FileReader();
      reader.onload = () => {
        this.imagePreview = reader.result;
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }
}

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { EntityGenerateService } from '../entity-generate.service';
@Component({
  selector: 'app-entity-input',
  templateUrl: './entity-input.component.html',
  styleUrls: ['./entity-input.component.css']
})
export class EntityInputComponent  implements OnInit {
  userForm: FormGroup;

  constructor(private fb: FormBuilder,private entityService : EntityGenerateService) {
    this.userForm = this.fb.group({
      className: ['', Validators.required],
      packageName: ['', Validators.required],
      artifactId: ['', Validators.required],
      groupId: ['', Validators.required],
      properties: this.fb.array([])  // Dynamic property fields
    });
  }

  ngOnInit(): void {
    this.addProperty();  // Add the first property field by default
  }

  // Get form array for properties
  get properties(): FormArray {
    return this.userForm.get('properties') as FormArray;
  }

  // Method to add a new property form group
  addProperty(): void {
    const propertyGroup = this.fb.group({
      propertyName: ['', Validators.required],
      dataType: ['', Validators.required],
      nullable: [false],
      defaultData: [''],
      mandatoryField: [true],
      dbColumnName: ['', Validators.required],
      minimumLength: [1],
      maximumLength: [50],
      enableEncryption: [false],
      uniqueProperty: [true]

      
    });
    this.properties.push(propertyGroup);
  }

  // Method to remove a property
  removeProperty(index: number): void {
    this.properties.removeAt(index);
  }

  // Submit the form data
  // Submit the form data
onSubmit(): void {
  if (this.userForm.valid) {
    this.entityService.generateProject(this.userForm.value).subscribe({
      next: (response) => {
        console.log('File generated at path:', response);
      },
      error: (error) => {
        console.log('Error:', error);
      },
      complete: () => {
        console.log('Request completed');
      }
    });
  }
}


  
}


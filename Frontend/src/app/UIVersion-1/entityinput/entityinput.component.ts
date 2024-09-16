import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EntityGenerateService } from 'src/app/ui-v2/entity-generate.service';

@Component({
  selector: 'app-entityinput',
  templateUrl: './entityinput.component.html',
  styleUrls: ['./entityinput.component.css']
})
export class EntityinputComponent {
  userForm: FormGroup;

  constructor(private fb: FormBuilder,private entityService : EntityGenerateService) {
    this.userForm = this.fb.group({
      className: ['', Validators.required],
      dbSchemaName: ['', Validators.required],
      dbTableName: ['', Validators.required],
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
      relationshipType: ['NA'],
      nullable: [false],
      defaultData: [''],
      mandatoryField: [true],
      dbColumnName: ['', Validators.required],
      minimumLength: [1],
      maximumLength: [50],
      enableEncryption: [false],
      uniqueProperty: [true],
      relationshipColumnName: ['']
    });
    this.properties.push(propertyGroup);
  }

  // Method to remove a property
  removeProperty(index: number): void {
    this.properties.removeAt(index);
  }

  // Submit the form data
  onSubmit(): void {
    if (this.userForm.valid) {
      this.entityService.generateProject(this.userForm.value).subscribe((response)=>{
        console.log('File generated at path:', response);
        


      },
      (error)=>{
        console.log('F', error);
      }
    )
      // Handle form submission, e.g., send it to a backend or save it
    }
  
  }

}

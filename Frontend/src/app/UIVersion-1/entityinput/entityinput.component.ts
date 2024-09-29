import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EntityGenerateService } from 'src/app/ui-v2/entity-generate.service';
import { MainserviceService } from '../mainservice.service';

@Component({
  selector: 'app-entityinput',
  templateUrl: './entityinput.component.html',
  styleUrls: ['./entityinput.component.css']
})
export class EntityinputComponent {
  userForm: FormGroup;
  projectData: any;
  //properties =[];
  constructor(private fb: FormBuilder,private entityService : EntityGenerateService,private mainService: MainserviceService) {
    this.userForm = this.fb.group({
      classes: this.fb.array([])
    });
  }

  ngOnInit(): void {
    this.addClass();  // Add the first property field by default
    console.log(this.mainService.getProjectData());
  }
  get classes(): FormArray {
    return this.userForm.get('classes') as FormArray;
  }

  // Get form array for properties
  properties(classIndex:number): FormArray {
    return this.classes.at(classIndex).get('properties') as FormArray;
  }
  addClass(): void{
    const classForm = this.fb.group({
      className: ['', Validators.required],
      dbSchemaName: ['', Validators.required],
      dbTableName: ['', Validators.required],
      properties: this.fb.array([],Validators.required)  // Dynamic property fields
    });
    this.classes.push(classForm);
  }
    // Method to remove a property
    removeClass(index: number): void {
      this.classes.removeAt(index);
    }

  // Method to add a new property form group
  // addProperty(i:number): void {
  //   const propertyGroup = this.fb.group({
  //     propertyName: ['', Validators.required],
  //     dataType: ['', Validators.required],
  //     relationshipType: ['NA'],
  //     nullable: [false],
  //     defaultData: [''],
  //     mandatoryField: [true],
  //     dbColumnName: ['', Validators.required],
  //     minimumLength: [1],
  //     maximumLength: [50],
  //     enableEncryption: [false],
  //     uniqueProperty: [true],
  //     relationshipColumnName: ['']
  //   });
  //   console.log(this.userForm.get('classes'))
  //   this.userForm.get('classes')?.value[i].properties.push(propertyGroup);
  //   console.log(this.userForm.get('classes'))
  // }
  newProperty(): FormGroup {
    return this.fb.group({
      propertyName: ['', Validators.required],
      dataType: ['', Validators.required],
      dbColumnName: ['', Validators.required],
      minimumLength: [0, Validators.min(0)],
      maximumLength: [1000, Validators.max(1000)],
      relationshipColumnName: [''],
      nullable: [false],
      uniqueProperty: [false],
      enableEncryption: [false],
      mandatoryField: [false],
      relationshipType: ['']
    });
  }

  addProperty(classIndex: number) {
    this.properties(classIndex).push(this.newProperty());
  }

  removeProperty(propertyIndex: number, classIndex: number) {
    this.properties(classIndex).removeAt(propertyIndex);
  }



  // Method to remove a property
  // removeProperty(index: number,classnum: number): void {
  //   //this.properties.removeAt(index);
  //   this.userForm.get('classes')?.value[classnum].properties.remove(index);
  // }

  // Submit the form data
  onSubmit(): void {
    console.log(this.userForm.value);
    console.log(this.mainService.getProjectData())
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

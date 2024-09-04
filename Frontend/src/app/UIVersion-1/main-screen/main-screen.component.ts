import { Component, ViewEncapsulation } from '@angular/core';
import {MainserviceService} from '../mainservice.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-main-screen',
  templateUrl: './main-screen.component.html',
  styleUrls: ['./main-screen.component.css'],
  encapsulation: ViewEncapsulation.Emulated 
})
export class MainScreenComponent {
  darktheme=false;
  projecttype=["Gradle - Groovy","Gradle - Kotlin","Maven"];
  springbootversionoptions=[];
  javaoptions=[];
  languageoptions=[];
  dependencies : Dependency[] = [];
  dependencyselected: String[] = [];
  selectedprojecttype=[];
  metadataForm:FormGroup;
  constructor(
    private mainserviceService : MainserviceService,
    private fb: FormBuilder
  ){
    this.getformdetails();
    this.metadataForm = this.fb.group({
        projecttype : ['',Validators.required],
        javaversion : ['',Validators.required],
        language : ['',Validators.required],
        springbootversion : ['',Validators.required],
        groupname : ['',Validators.required],
        artifactname : ['',Validators.required],
        name : ['',Validators.required],
        packagename : ['',Validators.required],
        description : ['',Validators.required],
        dependencies : ['',Validators.required]
    }

    );
  }
  getformdetails(){
     this.mainserviceService.getdropdownoptions().subscribe((data)=>{
         console.log(data);
         this.projecttype = [];
         this.springbootversionoptions = data.bootVersion.values;
         this.javaoptions = data.javaVersion.values;
         this.languageoptions = data.language.values;
        // this.dependencies = data.dependencies.values;
         this.dependencies = data.dependencies.values.map((dependency: Dependency) => {
          return {
            ...dependency,
            values: dependency.values.map(subdependency => ({
              ...subdependency,
              selected: false  // Assign default value if missing
            }))
          };
        });
        console.log(this.dependencies);
         this.metadataForm.patchValue({javaversion: data.javaVersion.default,
          groupname: data.groupId.default,
          projecttype: 'Maven',
          language: data.language.default,
          springbootversion: data.bootVersion.default,
          artifactname: data.artifactId.default,
          name: data.name.default,
          packagename: data.packageName.default,
          description: data.description.default,
        });
     })
  }
  adddependency(dep: SubCategory){
    let existvalue = false;
    this.dependencyselected.forEach(selected=>{
      if(selected==dep.name){
        existvalue=true;
      }
    })
    if(!existvalue){
      this.dependencyselected.push(dep.name);
    }
    else{
      this.dependencyselected=this.dependencyselected.filter(dependency=>dependency!=dep.name)
    }
     this.dependencies.forEach(dependency => {
      dependency.values.forEach(sd => {
        if(dep.name==sd.name){
        sd.selected = !sd.selected}});
    });
     this.metadataForm.patchValue({dependencies: this.dependencyselected});
  }
}
interface SubCategory {
  selected?:boolean
  id:String;
  name:String;
  versionRange:String;
  description:String;
}

interface Dependency {
  name: string;
  values: SubCategory[]; // Adjust the type based on your actual data structure
}

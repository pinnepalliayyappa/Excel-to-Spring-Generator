import { Component, ViewEncapsulation } from '@angular/core';
import {MainserviceService} from '../mainservice.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';

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
  currentColorday = 'white'
  currentColornight = 'black'
  
  constructor(
    private mainserviceService : MainserviceService,
    private fb: FormBuilder,
    private router: Router
    )
  {
    this.getformdetails();
    this.metadataForm = this.fb.group({
        projecttype : [,Validators.required],
        javaversion : [,Validators.required],
        language : [,Validators.required],
        springbootversion : [,Validators.required],
        groupname : ['',Validators.required],
        artifactname : ['',Validators.required],
        name : ['',Validators.required],
        packagename : ['',Validators.required],
        description : ['',Validators.required],
        dependencies : ['',Validators.required]
    });
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
      this.metadataForm.patchValue({
        javaversion: data.javaVersion.default,
        groupname: data.groupId.default,
        projecttype: 'maven-project',
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
      if(selected==dep.id){
        existvalue=true;
      }
    })
    if(!existvalue){
      this.dependencyselected.push(dep.id);
    }
    else{
      this.dependencyselected=this.dependencyselected.filter(dependency=>dependency!=dep.id)
    }
    this.dependencies.forEach(dependency => {
      dependency.values.forEach(sd => {
        if(dep.id==sd.id){
          sd.selected = !sd.selected
        }
      });
    });
    this.metadataForm.patchValue({dependencies: this.dependencyselected});
  }
  generateEntity(){
    this.mainserviceService.setProjectData(this.metadataForm.value);
    this.router.navigate(['entity']);

  }
  exploreProject(){
    console.log(this.metadataForm);
    let dependstring = '';
    //let dependentvalues =[];
    const dependentvalues =this?.metadataForm?.get('dependencies')?.value
    dependstring =dependentvalues?dependentvalues.join(",") :""
    let submitdetails={
      javaversion: this.metadataForm.get('javaversion')?.value,
      groupname: this.metadataForm.get('groupname')?.value,
      projecttype: this.metadataForm.get('projecttype')?.value,
      language: this.metadataForm.get('language')?.value,
      springbootversion: this.metadataForm.get('springbootversion')?.value,
      artifactname: this.metadataForm.get('artifactname')?.value,
      name: this.metadataForm.get('name')?.value,
      packagename: this.metadataForm.get('packagename')?.value,
      description: this.metadataForm.get('description')?.value,
      dependencies: this.metadataForm.get('dependencies')?.value.join(","),
    }
    console.log(submitdetails);
  }
  shareProject(){
    
  }
  toggle(theme:any){
       if(theme == 'dark'){
         this.darktheme = true;
       }
       else{
        this.darktheme = false;
       }
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
  values: SubCategory[]; 
}

import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {
  darktheme=false;

  initializerForm: FormGroup;

  constructor(private fb: FormBuilder) {
    this.initializerForm = this.fb.group({
      projectType: ['maven-project'],
      language: ['java'],
      groupId: ['com.example'],
      artifactId: ['demo'],
      dependencies: [[]],
      useSpringBoot: [true]
    });
  }

  generateProject() {
    const formValues = this.initializerForm.value;
    console.log('Generating project with:', formValues);
    // Logic to generate and download the project.
  }

}

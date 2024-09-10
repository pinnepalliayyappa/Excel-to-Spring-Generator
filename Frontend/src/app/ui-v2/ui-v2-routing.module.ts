// ui-v2-routing.module.ts
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EntityInputComponent } from './entity-input/entity-input.component';


const routes: Routes = [
  { path: 'entity-input', component: EntityInputComponent },
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],  // Use forChild for feature modules
  exports: [RouterModule]
})
export class UiV2RoutingModule { }

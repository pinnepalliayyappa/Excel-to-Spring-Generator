import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainScreenComponent } from './UIVersion-1/main-screen/main-screen.component';
import { EntityinputComponent } from './UIVersion-1/entityinput/entityinput.component';


const routes: Routes = [
  {
    path: 'ui-v2',
    loadChildren: () => import('./ui-v2/ui-v2.module').then(m => m.UiV2Module) 
  },
  { path: 'home', component: MainScreenComponent },
  { path: '', component: MainScreenComponent },
  { path: 'entity', component: EntityinputComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }

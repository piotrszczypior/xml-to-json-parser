import { Routes } from '@angular/router';
import {ParserPageComponent} from './pages/parser-page/parser-page.component';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'parse',
  },
  {
    path: 'parse',
    component: ParserPageComponent
  }
];

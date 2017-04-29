import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './components/app.component';
import { HeroDetailComponent } from './components/hero-detail.component';
import { HeroesComponent } from './components/heroes.component';
import { HeroService } from './services/hero.service';
import { RouterModule } from '@angular/router';
import { DashboardComponent } from './components/dashboard.component';
import { HeroSearchComponent } from './components/hero-search.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path: '',
        redirectTo: '/dashboard',
        pathMatch: 'full'
      },
      {
        path: 'heroes',
        component: HeroesComponent
      },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
      {
        path: 'detail/:id',
        component: HeroDetailComponent
      }
    ])
  ],
  declarations: [
    AppComponent,
    HeroDetailComponent,
    HeroesComponent,
    DashboardComponent,
    HeroSearchComponent
  ],
  providers: [
    HeroService
  ],
  bootstrap: [AppComponent]
})


export class AppModule {
}

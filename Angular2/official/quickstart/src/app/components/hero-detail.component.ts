/**
 * Created by alex on 11.04.17.
 */
import "rxjs/add/operator/switchMap";
import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";

import {Hero} from "../hero";
import {HeroService} from "../services/hero.service";
@Component({
  selector: 'hero-detail',
  templateUrl: '../view/hero-detail.component.html',
  styleUrls: ['../styles/hero-detail.component.css'],
})
export class HeroDetailComponent implements OnInit {
  hero: Hero;

  constructor(private heroService: HeroService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.heroService.getHero(+params['id']))
      .subscribe(hero => this.hero = hero);
  }

  goBack(): void {
    this.location.back();
  }
}


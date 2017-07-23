import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Recipe} from '../recipe.model';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {
  @Output() recipeWasSelected = new EventEmitter<Recipe>();
  recipes: Recipe[] = [
    new Recipe('First Recipe', 'This is a first simply test',
      'http://www.seriouseats.com/images/2015/09/20150914-pressure-cooker-recipes-roundup-09.jpg'),
    new Recipe('Second Recipe', 'This is a second simply test',
      'https://www.heartandstroke.ca/-/media/images/recipes/en/stir-fried-broccoli--red-peppers-and-beef-on-a-white-plate-with-chopsticks.ashx?bc=f7f7f7&w=1160&h=653&as=1&la=en&hash=8BD99241B605ADFE8621A0EC7427DA7FB492AD27')
  ];

  constructor() {
  }

  ngOnInit() {
  }

  onRecipeSelected(recipe: Recipe) {
    this.recipeWasSelected.emit(recipe);
  }
}

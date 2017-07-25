import {Recipe} from './recipe.model';
import {EventEmitter} from '@angular/core';

export class RecipeService {
  recipeSelected = new EventEmitter<Recipe>();

  recipes: Recipe[] = [
    new Recipe('First Recipe', 'This is a first simply test',
      'http://www.seriouseats.com/images/2015/09/20150914-pressure-cooker-recipes-roundup-09.jpg'),
    new Recipe('Second Recipe', 'This is a second simply test',
      'https://www.heartandstroke.ca/-/media/images/recipes/en/stir-fried-broccoli--red-peppers-and-beef' +
      '-on-a-white-plate-with-chopsticks.ashx?bc=f7f7f7&w=1160&h=653&as=1&la=en&hash=8BD99241B605ADFE8621' +
      'A0EC7427DA7FB492AD27')
  ];

  getRecipes() {
    return this.recipes.slice();
  }
}

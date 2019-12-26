import React from 'react';

import CollectionsOverview from "../../components/collections-overview/collections-overview.component";
import CategoryPage from "../category/category.component";

const ShopPage = ({match}) => {
    return (
        <div className='shop-page'>
            <Route exact path={`${match.path}`} component={CollectionsOverview}/>
            <Route path={`${match.path}/:categoryId`} component={CollectionsOverview}/>
        </div>
    )
};


export default ShopPage;

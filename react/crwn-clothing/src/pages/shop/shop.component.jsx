import React from 'react';
import {Route} from "react-router-dom";
import {connect} from "react-redux";
import CollectionsOverviewContainer from "../../components/collections-overview/collections-overview.container";
import CollectionPageContainer from "../collection/collection.container";
import ShopActionTypes from "../../redux/shop/shop.types";


class ShopPage extends React.Component {


    componentDidMount() {
        const {fetchCollectionsStart} = this.props;
        fetchCollectionsStart();
    }

    render() {
        const {match} = this.props;

        return (<div className='shop-page'>
            <Route exact path={`${match.path}`}
                   component={CollectionsOverviewContainer}/>
            <Route path={`${match.path}/:collectionId`}
                   component={CollectionPageContainer}/>
        </div>)
    }
}


const mapDispatchToProps = (dispatch) => ({
    fetchCollectionsStart: () => {
        dispatch({type: ShopActionTypes.FETCH_COLLECTIONS_START});
    },
});

export default connect(null, mapDispatchToProps)(ShopPage);

import React from 'react';
import {ReactComponent as Logo} from '../../assets/crown.svg';
import {connect} from 'react-redux';
import {createStructuredSelector} from 'reselect';
import CartIcon from '../cart-icon/cart-icon.component'
import CartDropdown from "../cart-dropdown/cart-dropdown.component";
import {selectCartHidden} from '../../redux/cart/cart.selector';
import {selectCurrentUser} from '../../redux/user/user.selectors';
import {HeaderContainer, LogoContainer, OptionLink, OptionsContainer} from "./header.styles";
import {signOutStart} from "../../redux/user/user.actions";


const Header = ({currentUser, hidden, signOutStart}) => (
    <HeaderContainer>
        <LogoContainer to="/">
            <Logo className='logo'/>
        </LogoContainer>
        <OptionsContainer>
            <OptionLink to='/shop'>SHOP</OptionLink>
            <OptionLink to='/shop'>CONTACT</OptionLink>

            {
                currentUser ?
                    <OptionLink as='div' onClick={signOutStart}>SIGN OUT</OptionLink>
                    :
                    <OptionLink to='/signIn'>SIGN IN</OptionLink>
            }
            <CartIcon/>
        </OptionsContainer>

        {
            hidden ? null : <CartDropdown/>
        }
    </HeaderContainer>
);

const mapStateToProps = createStructuredSelector({
    currentUser: selectCurrentUser,
    hidden: selectCartHidden
});
const mapStateDispatchToProps = dispatch => ({
    signOutStart: () => dispatch(signOutStart())
});
export default connect(mapStateToProps, mapStateDispatchToProps)(Header);

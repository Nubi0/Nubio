import styled from "styled-components";

export const FooterWrapper = styled.div`
  width: 100%;
  position: absolute;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  place-items: center;
  background-color: white;
`;

export const MenuImgBox = styled.div`
  position: absolute;
  right: 1rem;
  bottom: 1rem;
  z-index: 13!important;
  width: 60px;
  height: 60px;
  background-color: red;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 1rem;
  transform: scale(1);
`;

export const MenuBox = styled.div`
  position: absolute;
  right: 0;
  bottom: 0;
  transform: scale(0);
  width: 15em;
  height: 15rem;
  background: #f00;
  border-radius: 15rem 0 0 0;
  transform-origin: bottom right;
  -webkit-transform: scale(0);
  -moz-transform: scale(0);
  -webkit-transition: all 0.4s ease-out;
  -moz-transition: all 0.4s ease-out;
  transition: all 0.4s ease-out;
`

export const MenuToggle = styled.div`
  img{
    z-index: 11;
  }
  &.tab.active {
      .menuBox {
        -webkit-transform: scale(1);
        -moz-transform: scale(1);
        transform: scale(1);
      }
      .icon {
        -webkit-transform: scale(1);
        -moz-transform: scale(1);
        transform: scale(1);
      }
  }
`;

export const MenuImg = styled.img`
`;

export const MenuItemWrapper = styled.div`
  border: 1px solid black;
  width: 80%;
  font-size: 1.5rem;
  text-align: start;
  display: flex;
  justify-content: center;
  margin-bottom: 1rem;
  border-radius: 0.5rem;
`;

export const CloseButtonWrapper = styled.div`
  border: 1px solid black;
  border-radius: 0.5rem;
  width: 80%;
  display: flex;
  justify-content: center;
  font-size: 1.5rem;
  margin-bottom: 1rem;
  margin-top: 1.5rem;
`;

export const SafeModalWrapper = styled.div``;

export const ModalTitle = styled.div``;

export const MenuList = styled.div``;
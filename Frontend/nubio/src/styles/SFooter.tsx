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


export const MenuToggle = styled.div`
  img{
    z-index: 11;
    position: relative;
  }
  div{
    position: absolute;
    left: 50%;
    transform: translateX(-50%);
    width: 200px;
    height: 100px;
    background: #f00;
    border-bottom-left-radius: 100px;
    border-bottom-right-radius: 100px;
    transform-origin: top center;
    transition: 1s ease-in-out;
  }
  ul {
    position: absolute;
    display: flex;
    justify-content: center;
    align-items: center;
    inset: 0;
  }
  li {
    list-style: none;
    display: flex;
    position: absolute;
    cursor: pointer;
    img {
      transform: rotate(180deg);
    }
  }
  li:nth-child(1) {
    transform: translate(50px, -25px);
  }
  li:nth-child(2) {
    transform: translate(10px, 15px);
  }
  li:nth-child(3) {
    transform: translate(-50px, 15px);
  }
  li:nth-child(4) {
    transform: translate(-85px, -25px);
  }
  .cbg1 {
    background: #8bc34a!important;
    transition: 0.6s ease-in-out;
    transition-delay: 0.4s;
  }
  .cbg2 {
    background: #ffeb3b!important;
    transition: 0.8s ease-in-out;
    transition-delay: 0.2s;
  }
  &.tab.active {
      div{
        transform: translateX(-50%) rotate(180deg);
        z-index: 10;
      }
      .cb1{
        transition-delay: 0s;
      }
      .cb2{
        transition-delay: 0s;
      }
  }
`;


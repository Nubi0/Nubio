import styled from "styled-components";


export const ProfilePageWrapper = styled.div`
  display: flex;
  flex-direction: column;
  height: 100%;
  position: relative;
`;

export const SaveButton = styled.button`
  position: absolute;
  top: 1rem;
  right: 1rem;
  border: none;
  background-color: white;
`;

export const ProfileWrapper = styled.div`
  display:flex;
  flex-direction: column;
  height: 100vh;
`;
export const MyInfoWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 1rem;
  background-color: white;
  div{
    display: flex;
    width: 80%;
  }
  button{
    border: none;
    background-color: transparent;
  }
  input{
    border: none;
    font-size: 1.17rem;
    color: gray;
    width: 70%;
    margin-left: 0.1rem;
    :focus{
      border: none;
      color: black;
    }
  }
  label{
    display: flex;
  }
`;

export const Title = styled.h3`
  width: 5rem;
`;

export const Content = styled.h3`
  color: gray;
`;

export const NickName = styled.div`
  display: flex;

`;

export const ProfileImg = styled.div`
  justify-content: center;
  position: relative;
`;

export const MyImg = styled.img`
  width: 4rem;
  height: 4rem;
  display: block;
  border-radius: 50%;
`;

export const ImgPlus = styled.img`
  position: absolute;
  bottom: -0.25rem;
  right: 7.5rem;
`;

export const SettingWrapper = styled.div`
  height: 60%;
  padding: 1rem;
  div {
    padding: 0.1rem 0;
    // margin: 1rem 0;
    width: 100%;
    background-color: white;
  }
  hr {
    margin: 0;
  }
`;

export const LogOutWrapper = styled.div``;
export const DeleteUserWrapper = styled.div``;
export const MyListWrapper = styled.div``;
export const MyRefrenceWrapper = styled.div``;
export const GenderWrapper = styled.div`
  display: flex;
  #man,
  #woman {
    display: none;
  }
  #manCheck {
    border-radius:1rem;
    background-color: #2E9AFE;
  }
  #womanCheck {
    border-radius:1rem;
    background-color: pink;
  }
`;
export const ManIcon = styled.img`
  width: 3rem;
  height: 3rem;
`;
export const WomanIcon = styled.img`
  width: 3rem;
  height: 3rem;
`;
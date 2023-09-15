import styled from "styled-components";


export const ProfilePageWrapper = styled.div``;

export const ProfileWrapper = styled.div`
  height: 100%;
  padding: 1rem;
`;
export const MyInfoWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 40%;
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
    width: 80%;
    margin-left: 0.1rem;
    :focus{
      border: none;
      color: black;
    }
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

export const MyImg = styled.img`
  width: 4rem;
    height: 4rem;
    display: block;
`;

export const SettingWrapper = styled.div`
  width: 100%;
  height: 60%;
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

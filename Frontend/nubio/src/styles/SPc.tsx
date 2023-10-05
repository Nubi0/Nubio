import styled from "@emotion/styled";

export const Wrapper = styled.div`
  width: 100vw;
  display: flex;
  #pcBackgorund {
    position: absolute;
    z-index: -1;
    width: 100%;
    height: 100%;
    opacity: 0.5;
  }
`;

export const PcWrapper = styled.div`
  width: 70%;
`;

// 제목
export const TitleWrapper = styled.div`
  text-align: center;
  img {
    width: 30rem;
  }
  h1 {
    margin: 0 0.5rem;
    font-size: 3rem;
  }
`;

export const TitleTextWrapper = styled.div`
  display: flex;
  justify-content: center;
  #safe {
    color: #41d992;
  }
  #date {
    color: #ffc542;
  }
`;

// 내용

export const ContentWrapper = styled.div`
  #recommend {
    transform: rotate(-10deg);
  }
  #custom {
    transform: rotate(-10deg);
  }
  img {
    width: 8%;
  }
`;

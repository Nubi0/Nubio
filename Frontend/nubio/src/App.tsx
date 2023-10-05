import { BrowserRouter, Routes, Route } from "react-router-dom";
import routes from "./router";
import { Provider } from "react-redux";
import { store } from "./redux/store";
import {
  ContentWrapper,
  PcWrapper,
  TitleTextWrapper,
  TitleWrapper,
  Wrapper,
} from "./styles/SPc";
function App() {
  const logo = process.env.PUBLIC_URL + "/assets/nubio.png";
  const happyBackground = process.env.PUBLIC_URL + "/assets/pc/happy.avif";
  const recommendIcon = process.env.PUBLIC_URL + "/assets/pc/recommendIcon.png";
  const customIcon = process.env.PUBLIC_URL + "/assets/pc/customIcon.png";
  const safeICon = process.env.PUBLIC_URL + "/assets/pc/safeICon.png";
  return (
    <Wrapper>
      <img src={happyBackground} alt="배경" id="pcBackgorund" />
      <PcWrapper>
        <TitleWrapper>
          <img src={logo} alt="로고" id="logo" />
          <TitleTextWrapper>
            <h1 id="safe">안전하고 </h1>
            <h1 id="date">즐거운 데이트</h1>
            <h1 id="share">를 위한</h1>
          </TitleTextWrapper>
          <h1>지도 공유 서비스</h1>
        </TitleWrapper>
        <ContentWrapper>
          <div>
            <img src={recommendIcon} alt="" />
            <h1 id="recommend">취향을 바탕으로 맞춤형 코스 추천</h1>
          </div>
          <div>
            <img src={customIcon} alt="" />
            <h1 id="custom">코스를 직접 커스텀</h1>
          </div>
          <div>
            <img src={safeICon} alt="" />
            <h1 id="safe">목적지까지 안전한 길 추천!</h1>
          </div>
        </ContentWrapper>
      </PcWrapper>
      <Provider store={store}>
        <div
          className="App"
          style={{
            width: "430px",
            height: "932px",
            border: "0.3rem solid black",
            marginTop: "3rem",
          }}
        >
          <BrowserRouter>
            <Routes>
              {routes.map((e) => {
                return (
                  <Route
                    key={e.path}
                    path={e.path}
                    element={<e.Component></e.Component>}
                  />
                );
              })}
            </Routes>
          </BrowserRouter>
        </div>
      </Provider>
    </Wrapper>
  );
}

export default App;

import { AppContentWrapper, Discuss } from "../../styles/SHomePage";

const AppContent = () => {
  const safeIcon = process.env.PUBLIC_URL + "/assets/startPage/safeIcon.png";
  const customIcon =
    process.env.PUBLIC_URL + "/assets/startPage/customIcon.png";
  const recommendIcon =
    process.env.PUBLIC_URL + "/assets/startPage/recommendIcon.png";
  const reportIcon =
    process.env.PUBLIC_URL + "/assets/startPage/reportIcon.png";
  return (
    <AppContentWrapper>
      <div className="left">
        <img src={customIcon} alt="" />
        <p>코스를 직접 커스텀</p>
      </div>
      <div className="right">
        <p>취향을 바탕으로 맞춤혁 코스 추천</p>
        <img src={recommendIcon} alt="" />
      </div>
      <div className="left">
        <img src={safeIcon} alt="" />
        <p>목적지까지 안전한 길 추천</p>
      </div>

      <div className="right">
        <p>위험구역 제보 및 공유</p>
        <img src={reportIcon} alt="" />
      </div>
    </AppContentWrapper>
  );
};

export default AppContent;

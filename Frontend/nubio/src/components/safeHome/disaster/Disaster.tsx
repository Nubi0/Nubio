import { DisasterWrapper, DisasterIcon } from "../../../styles/SDisaster";
interface EvacuationModalProps {
  openEvacuationModal: (Evacuation: DisasterIconInfo) => void;
}
const Disaster: React.FC<EvacuationModalProps> = ({ openEvacuationModal }) => {
  const earthQuake: DisasterIconInfo = {
    src: process.env.PUBLIC_URL + "/assets/disaster/earthquake.svg",
    name: "지진",
    content:
      "1. 지진 발생 시 안전한 장소로 빠르게 이동하세요. 가능하면 개방된 공간 또는 안전한 건물로 피하세요.\n" +
      "2. 지진 발생 시 무릎을 굽히고 땅에 엎드려 몸을 보호하고, 머리와 목을 가려주세요.\n" +
      "3. 홍수와 화재에 주의: 지진으로 인해 홍수 또는 화재 위험이 있을 경우 대피로 안전한 위치로 이동하고, 응급 상황에 대비한 대피 가방과 필수품을 가지고 나가세요.",
  };

  const fire: DisasterIconInfo = {
    src: process.env.PUBLIC_URL + "/assets/disaster/fire.svg",
    name: "화재",
    content:
      "즉시 119에 신고: 화재가 발생하면 즉시 119에 신고하고, 화재경보장치를 작동시키세요.\n" +
      "낮은 자세로 이동: 연기에 매몰되지 않도록 낮은 자세로 기어가거나 기어 나가세요.\n" +
      "출구를 활용: 가장 가까운 안전한 출구를 향해 이동하며, 엘리베이터는 사용하지 말고 계단을 이용하세요.",
  };

  const hot: DisasterIconInfo = {
    src: process.env.PUBLIC_URL + "/assets/disaster/hot.svg",
    name: "폭염",
    content:
      "물을 충분히 섭취: 물을 충분히 마시고, 머리를 가려서 직사광선을 피하세요.\n" +
      "선제적인 냉방: 에어컨이나 선풍기를 사용하고, 실내에서 머무르며 실외 열을 피하세요.\n" +
      "신체 냉각: 시원한 물에 몸을 담그거나 습한 수건으로 몸을 닦아 체온을 낮추세요.",
  };

  const HeavyRain: DisasterIconInfo = {
    src: process.env.PUBLIC_URL + "/assets/disaster/heavyRain.svg",
    name: "폭우",
    content:
      "안전한 장소로 이동: 폭우 예상 시, 주변 지역이 침수될 수 있으므로 안전한 지역으로 이동하거나 높은 곳으로 피하세요.\n" +
      "통신 수단 활용: 비상 상황에서 연락이 끊길 수 있으므로 휴대폰이나 무선 통신 장치를 사용하여 도움을 요청하거나 안전 상황을 확인하세요.\n" +
      "길을 피하고 대피용품 지참: 침수된 도로와 다리를 피하고, 비상 대피 가방과 필수품을 가지고 있어야 합니다.",
  };

  const snowFlood: DisasterIconInfo = {
    src: process.env.PUBLIC_URL + "/assets/disaster/snowFlood.svg",
    name: "폭설",
    content:
      "운전 주의: 도로가 미끄러울 수 있으므로 운전 시 주의하고, 필요한 경우 타이어 체인을 사용하세요.\n" +
      "따뜻한 의류와 식량 준비: 긴 시간 동안 실외에서 머무를 수 있도록 따뜻한 의류와 급식을 가지고 다니세요.\n" +
      "폭설 경보 주시: 정부 또는 관할 당국의 폭설 경보를 주시하고, 집 밖으로 나가기 전에 길 상태를 확인하세요.",
  };

  const terror: DisasterIconInfo = {
    src: process.env.PUBLIC_URL + "/assets/disaster/terror.svg",
    name: "테러",
    content:
      "안전한 장소로 이동: 테러 발생 시 즉시 안전한 장소로 피하고, 경찰 또는 관할 당국에 연락하세요.\n" +
      "휴대폰 사용 주의: 폭발 위험이 있을 경우 휴대폰 사용을 피하고, 무선 통신을 끄세요.\n" +
      "대피 경로 숙지: 대피 경로를 알고 있고, 다른 사람들과 협력하여 안전한 곳으로 이동하세요.",
  };

  return (
    <DisasterWrapper>
      <div>
        <DisasterIcon
          src={fire.src}
          onClick={() => openEvacuationModal(fire)}
        />
        <p>{fire.name}</p>
      </div>
      <div>
        <DisasterIcon src={hot.src} onClick={() => openEvacuationModal(hot)} />
        <p>{hot.name}</p>
      </div>
      <div>
        <DisasterIcon
          src={HeavyRain.src}
          onClick={() => openEvacuationModal(HeavyRain)}
        />
        <p>{HeavyRain.name}</p>
      </div>
      <div>
        <DisasterIcon
          src={snowFlood.src}
          onClick={() => openEvacuationModal(snowFlood)}
        />
        <p>{snowFlood.name}</p>
      </div>
      <div>
        <DisasterIcon
          src={earthQuake.src}
          onClick={() => openEvacuationModal(earthQuake)}
        />
        <p>{earthQuake.name}</p>
      </div>
      <div>
        <DisasterIcon
          src={terror.src}
          onClick={() => openEvacuationModal(terror)}
        />
        <p>{terror.name}</p>
      </div>
    </DisasterWrapper>
  );
};

export default Disaster;

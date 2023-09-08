import { DisasterWrapper, DisasterIcon } from "../../styles/SSafeHomePage";

const Disaster = () => {
  const earthQuake = process.env.PUBLIC_URL + "/assets/disaster/earthquake.svg";
  const fire = process.env.PUBLIC_URL + "/assets/disaster/fire.svg";
  const hot = process.env.PUBLIC_URL + "/assets/disaster/hot.svg";
  const snowFlood = process.env.PUBLIC_URL + "/assets/disaster/snowFlood.svg";
  const terror = process.env.PUBLIC_URL + "/assets/disaster/terror.svg";
  return (
    <DisasterWrapper>
      <span>
        <DisasterIcon src={earthQuake} />
        <p>지진</p>
      </span>
      <span>
        <DisasterIcon src={fire} />
        <p>화재</p>
      </span>
      <span>
        <DisasterIcon src={hot} />
        <p>폭염</p>
      </span>
      <span>
        <p>폭우 폭설</p>
        <DisasterIcon src={snowFlood} />
      </span>
      <span>
        <DisasterIcon src={terror} />
        <p>테러</p>
      </span>
    </DisasterWrapper>
  );
};

export default Disaster;

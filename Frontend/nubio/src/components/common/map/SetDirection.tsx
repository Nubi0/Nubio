import { SetDirectionWrapper } from "../../../styles/SKakaoMap";

const SetDirection = () => {
  const startName = localStorage.getItem("startName")!;
  const endtName = localStorage.getItem("endtName")!;
  return (
    <SetDirectionWrapper>
      <input type="text" placeholder="출발지" value={startName} />
      <input type="text" placeholder="도착지" value={endtName} />
    </SetDirectionWrapper>
  );
};

export default SetDirection;

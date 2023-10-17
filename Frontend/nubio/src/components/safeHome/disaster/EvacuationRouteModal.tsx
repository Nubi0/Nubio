import { EvacuationRouteModalBox } from "../../../styles/SDisaster";
interface EvacuationRouteModalProps {
  closeModal: () => void;
  Evacuation: DisasterIconInfo;
}
const EvacuationRouteModal: React.FC<EvacuationRouteModalProps> = ({
  closeModal,
  Evacuation,
}) => {
  return (
    <EvacuationRouteModalBox>
      <h1>{Evacuation.name} 시 대피 요령</h1>
      <p>{Evacuation.content}</p>
      <button onClick={closeModal}>닫기</button>
    </EvacuationRouteModalBox>
  );
};

export default EvacuationRouteModal;

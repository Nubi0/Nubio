import { CloseButtonWrapper } from "@styles/SFooter";

const CloseButton = ({ setModal }: { setModal: () => void }) => {
  return (
    <CloseButtonWrapper onClick={setModal}>
      <div>닫기</div>
    </CloseButtonWrapper>
  );
};

export default CloseButton;

import { useState } from "react";
import { SafeGuideModalBox, SafeGuideIcon } from "@styles/SDisaster";
import EvacuationRouteModal from "./EvacuationRouteModal";
import Disaster from "./Disaster";

const SafeGuideModal = () => {
  //이미지
  const safeGuide = process.env.PUBLIC_URL + "/assets/safeGuide.png";

  const [isOpen, setIsOpen] = useState(false);
  const openModal = () => {
    setIsOpen(true);
  };
  const closeModal = () => {
    setIsOpen(false);
  };

  // 대피경로 모달
  const [isEvacuationOpen, setEvacuationIsOpen] = useState(false);
  const [selectedEvacuation, setSelectedEvacuation] =
    useState<DisasterIconInfo | null>(null);

  const openEvacuationModal = (Evacuation: DisasterIconInfo) => {
    setSelectedEvacuation(Evacuation);
    setEvacuationIsOpen(true);
  };

  const closeEvacuationModal = () => {
    setSelectedEvacuation(null);
    setEvacuationIsOpen(false);
  };
  return (
    <>
      <SafeGuideIcon src={safeGuide} alt="가이드북" onClick={openModal} />
      <h2>대피 가이드</h2>
      {isOpen ? (
        <SafeGuideModalBox>
          {isOpen && selectedEvacuation ? (
            <EvacuationRouteModal
              closeModal={closeEvacuationModal}
              Evacuation={selectedEvacuation}
            />
          ) : (
            <>
              <Disaster openEvacuationModal={openEvacuationModal} />
              <button id="close" onClick={closeModal}>
                닫기
              </button>
            </>
          )}
        </SafeGuideModalBox>
      ) : null}
    </>
  );
};

export default SafeGuideModal;

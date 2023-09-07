import { useState } from "react";
import {
  PrefrenceModalBox,
  PrefrenceModalOverlay,
} from "../../../styles/SSignUpPage";
import DrinkList from "./DrinkList";
import EatList from "./EatList";
import PlayList from "./PlayList";

type SetPrefrenceModalProps = {
  closeModal: () => void;
};

const SetPrefrenceModal: React.FC<SetPrefrenceModalProps> = ({
  closeModal,
}) => {
  const [selectedImages, setSelectedImages] = useState<string[]>([]);
  const handleImageClick = (name: string) => {
    const isSelected = selectedImages.includes(name);
    if (isSelected) {
      setSelectedImages(selectedImages.filter((image) => image !== name));
    } else {
      setSelectedImages([...selectedImages, name]);
    }
  };
  return (
    <PrefrenceModalOverlay>
      <PrefrenceModalBox>
        <DrinkList
          handleImageClick={handleImageClick}
          selectedImages={selectedImages}
        />
        <EatList
          handleImageClick={handleImageClick}
          selectedImages={selectedImages}
        />
        <PlayList
          handleImageClick={handleImageClick}
          selectedImages={selectedImages}
        />
        <button onClick={closeModal}>Close Modal</button>
      </PrefrenceModalBox>
    </PrefrenceModalOverlay>
  );
};

export default SetPrefrenceModal;

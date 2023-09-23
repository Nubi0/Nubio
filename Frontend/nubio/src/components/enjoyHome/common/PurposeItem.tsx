import { PurposeItemWrapper } from '../../../styles/SAllCoursePage';

type PurposeProps = {
  purpose: string;
  handleClick: () => void;
  isSelected: boolean;
};

const PurposeItem = ({ purpose, handleClick, isSelected }: PurposeProps) => {
  return (
    <PurposeItemWrapper onClick={handleClick} isSelected={isSelected}>
      {purpose}
    </PurposeItemWrapper>
  );
};

export default PurposeItem;

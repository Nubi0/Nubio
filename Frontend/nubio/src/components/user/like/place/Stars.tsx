import Box from '@mui/material/Box';
import Rating from '@mui/material/Rating';

type scoreProps = {
  score: number;
};

const Stars = ({ score }: scoreProps) => {
  return (
    <Box>
      <Rating value={score} readOnly />
    </Box>
  );
};

export default Stars;

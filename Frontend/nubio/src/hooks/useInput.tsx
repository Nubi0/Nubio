import { useCallback, useState, Dispatch, SetStateAction } from "react";

type UseInputType = [
  string,
  (e: React.ChangeEvent<HTMLInputElement>) => void,
  Dispatch<SetStateAction<string>>,
];

const useInput = (initialValue: string): UseInputType => {
  const [value, setValue] = useState<string>(initialValue);

  const handler = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
    setValue(e.target.value);
  }, []);

  return [value, handler, setValue];
};

export default useInput;

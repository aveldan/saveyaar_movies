import * as React from "react"
import Svg, { Path } from "react-native-svg"
const BackArrow = (props) => (
  <Svg
    xmlns="http://www.w3.org/2000/svg"
    width={props?.width || 24}
    height={props?.height || 24}
    fill="none"
    {...props}
  >
    <Path
      fill="#9C7CDE"
      fillRule="evenodd"
      d="M0 7.959c4.034 1.548 7.316 4.1 9.808 7.445-.04-2.007-.752-4.057-1.78-6.148h22.859V6.83H8.028c.95-1.715 1.543-3.765 1.7-6.233C6.922 3.985 3.837 6.704 0 7.96Z"
      clipRule="evenodd"
    />
  </Svg>
)
export { BackArrow }

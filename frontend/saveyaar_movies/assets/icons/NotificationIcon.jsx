import * as React from "react"
import Svg, { Mask, Path, G } from "react-native-svg"
const NotificationIcon = (props) => (
  <Svg
    xmlns="http://www.w3.org/2000/svg"
    width={props?.width || 24}
    height={props?.height || 24}
    fill="none"
    {...props}
  >
    <Mask
      id="a"
      width={21}
      height={19}
      x={0}
      y={0}
      maskUnits="userSpaceOnUse"
      style={{
        maskType: "luminance",
      }}
    >
      <Path
        fill="#fff"
        fillRule="evenodd"
        d="M.62.422h19.503v17.875H.62V.422Z"
        clipRule="evenodd"
      />
    </Mask>
    <G mask="url(#a)">
      <Path
        fill="#fff"
        fillRule="evenodd"
        d="M10.37 1.969c-3.685 0-6.254 2.821-6.254 5.353 0 2.142-.608 3.132-1.146 4.006-.431.702-.772 1.256-.772 2.46.177 1.944 1.49 2.965 8.172 2.965 6.646 0 8-1.066 8.175-3.031-.004-1.138-.344-1.692-.776-2.394-.537-.874-1.146-1.864-1.146-4.006 0-2.532-2.568-5.353-6.253-5.353Zm0 16.328c-4.93 0-9.386-.34-9.75-4.444-.003-1.698.528-2.561.996-3.322.473-.77.92-1.497.92-3.21 0-3.332 3.147-6.9 7.834-6.9 4.687 0 7.835 3.568 7.835 6.9 0 1.713.446 2.44.92 3.21.468.76.998 1.624.998 3.256-.368 4.17-4.823 4.51-9.753 4.51Z"
        clipRule="evenodd"
      />
    </G>
    <Path
      fill="#fff"
      fillRule="evenodd"
      d="M10.32 22.575h-.003c-1.182 0-2.3-.51-3.149-1.434a.76.76 0 0 1 .059-1.09.802.802 0 0 1 1.117.057c.546.595 1.247.921 1.974.921h.002c.73 0 1.434-.326 1.982-.922a.804.804 0 0 1 1.116-.056.76.76 0 0 1 .06 1.09c-.853.925-1.973 1.434-3.159 1.434Z"
      clipRule="evenodd"
    />
  </Svg>
)
export { NotificationIcon }
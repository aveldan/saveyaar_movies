import React, { useEffect, useState } from "react";
import Animated, { runOnJS, useAnimatedStyle, useSharedValue, withSpring, withTiming } from "react-native-reanimated";

import { width } from "../constants/Dimensions";
import TopRightGradient from "./TopRightGradient";
import { useSafeAreaInsets } from "react-native-safe-area-context";

interface Props {
    children: React.ReactNode;
    isActive: boolean;
    slideDirection: 'left' | 'right';
}

export default function TabScreenWrapper({children, isActive, slideDirection}: Props) {

    const [isAnimating, setIsAnimating] = useState(false);

    const translateX = useSharedValue(slideDirection === 'right'? -width : width);
    const opacity = useSharedValue(0);

    useEffect(() => {
        setIsAnimating(true);
        if(isActive){
            let animationsCompleted = 0;
            translateX.value = withSpring(0, {
                damping: 10,
                stiffness: 120,
                mass: 0.5
            }, () => {
                animationsCompleted += 1;
                if(animationsCompleted === 2){
                    runOnJS(setIsAnimating)(false);
                }
            });
            opacity.value = withTiming(1, { duration: 100 }, 
                () => {
                    animationsCompleted += 1;
                    if(animationsCompleted === 2){
                        runOnJS(setIsAnimating)(false);
                    }
                });
        }else{
            let animationsCompleted = 0;
            translateX.value = withSpring(slideDirection === 'right' ? -width : width, {
                damping: 10,
                stiffness: 120,
                mass: 0.5
            }, () => {
                animationsCompleted += 1;
                if(animationsCompleted === 2){
                    runOnJS(setIsAnimating)(false);
                }
            });
            opacity.value = withTiming(0, { duration: 100 },
                () => {
                    animationsCompleted += 1;
                    if(animationsCompleted === 2){
                        runOnJS(setIsAnimating)(false);
                    }
                });
        }
    }, [isActive, slideDirection]);   

    const animatedStyle = useAnimatedStyle(() => ({
        transform: [{ translateX: translateX.value }],
        opacity: opacity.value,
    }));

    const insets = useSafeAreaInsets();

    return (
        <TopRightGradient>
            <Animated.View
                style={[
                    {
                        position: 'absolute',
                        width: '100%',
                        height: '100%',
                        paddingTop: insets.top+10,
                        paddingBottom: 52,
                        paddingHorizontal: 10,
                    },
                    isAnimating ? animatedStyle : null
                ]}
            >
                {children}
            </Animated.View>
        </TopRightGradient>
    )
}
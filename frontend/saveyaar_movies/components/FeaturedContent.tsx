import { IMAGE_URL, TMDB_KEY } from "@env";
import React, { useEffect, useState } from "react";
import { Image, StyleSheet, TouchableOpacity } from "react-native";

interface FeaturedContent {
    imageUrl: string,
    handleOnPress: () => void
};

export default function FeaturedContent ({imageUrl, handleOnPress}: FeaturedContent) {
    
    const [imageData, setImageData] = useState<string | null>(null);

    useEffect(() => {
        const fetchImage = async () => {
            try {
                const response = await fetch(IMAGE_URL+imageUrl, {
                  headers: {
                    Authorization: `Bearer ${TMDB_KEY}`,
                  },
                });
                const blob = await response.blob();
                const reader = new FileReader();

                reader.onloadend = () => {
                    if (typeof reader.result === 'string') {
                        setImageData(reader.result);
                    } else {
                        console.error('Expected string from FileReader, got:', typeof reader.result);
                    }
                };
                
                reader.readAsDataURL(blob);
            } catch (error) {
                console.error('Error fetching image:', error);
            }
        }

        fetchImage();
    }, [imageUrl]);
    
    return (
        <TouchableOpacity style={styles.container} onPress={handleOnPress}>
            {imageData && 
                <Image source={{uri: imageData}} style={styles.image}/>
            }
        </TouchableOpacity>
    )
};

const styles = StyleSheet.create({
    container: {
        alignItems: 'center',
        paddingVertical: 15,
    },
    image: {
        borderRadius: 14,
        width: 354,
        height: 177,
    }
});
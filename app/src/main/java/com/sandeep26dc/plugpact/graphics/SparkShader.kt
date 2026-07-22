package com.sandeep26dc.plugpact.graphics

const val SPARK_SHADER_SRC = """
    uniform float2 resolution;
    uniform float time;
    
    vec4 main(vec2 fragCoord) {
        vec2 uv = (fragCoord.xy - resolution.xy * 0.5) / resolution.y;
        float dist = length(uv);
        float spark = 0.015 / abs(dist - 0.4 + sin(time * 4.0 + uv.x * 8.0) * 0.01);
        vec3 color = vec3(0.0, 0.9, 1.0) * spark;
        return vec4(color, color.r > 0.1 ? 1.0 : 0.0);
    }
"""
